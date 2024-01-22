package com.example.chapter02_6.userlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter02_6.Key.Companion.DB_CHAT_ROOMS
import com.example.chapter02_6.Key.Companion.DB_USERS
import com.example.chapter02_6.R
import com.example.chapter02_6.chatdetail.ChatActivity
import com.example.chapter02_6.chatdetail.ChatActivity.Companion.EXTRA_CHAT_ROOM_ID
import com.example.chapter02_6.chatdetail.ChatActivity.Companion.EXTRA_OTHER_USER_ID
import com.example.chapter02_6.chatlist.ChatRoomItem
import com.example.chapter02_6.databinding.FragmentUserlistBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.UUID

class UserFragment : Fragment(R.layout.fragment_userlist) {

    private lateinit var binding: FragmentUserlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        val userListAdapter = UserAdapter { otherUser ->
            val myUserId = Firebase.auth.currentUser?.uid ?: ""
            val chatRoomDB = Firebase.database.reference.child(DB_CHAT_ROOMS).child(myUserId).child(otherUser.userId ?: "")

            chatRoomDB.get().addOnSuccessListener {
                var chatRoomId = ""
                if (it.value != null) {
                    val chatRoom = it.getValue(ChatRoomItem::class.java)
                    chatRoomId = chatRoom?.chatRoomId ?: ""
                } else {
                    chatRoomId = UUID.randomUUID().toString()
                    val newChatRoom = ChatRoomItem(
                        chatRoomId = chatRoomId,
                        otherUserName = otherUser.username,
                        otherUserId = otherUser.userId,
                    )
                    chatRoomDB.setValue(newChatRoom)
                }

                val intent = Intent(context, ChatActivity::class.java)
                intent.putExtra(EXTRA_OTHER_USER_ID, otherUser.userId)
                intent.putExtra(EXTRA_CHAT_ROOM_ID, chatRoomId)

                startActivity(intent)
            }
        }

        binding.userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        val currentUserId = Firebase.auth.currentUser?.uid ?: ""

        Firebase.database.reference.child(DB_USERS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val userItemList = mutableListOf<UserItem>()

                    snapshot.children.forEach {
                        val user = it.getValue(UserItem::class.java)
                        user ?: return

                        if (user.userId != currentUserId) {
                            userItemList.add(user)
                        }
                    }

                    userListAdapter.submitList(userItemList)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}