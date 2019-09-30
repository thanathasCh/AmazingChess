package com.example.androidmidterm.Providers

import android.util.Log
import com.example.androidmidterm.Services.UserModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class User {

    private val db = FirebaseDatabase.getInstance().getReference("Users")
    private var users = ArrayList<UserModel>()

//     fun getValue() {
//         val done = AtomicBoolean(false)
//         db.addListenerForSingleValueEvent(object: ValueEventListener {
//             override fun  onDataChange(snapshot: DataSnapshot) {
//                 val data = snapshot.children
//                 val buf = ArrayList<UserModel>()
//                 data.forEach {
//                     val user = UserModel()
//                     with(user) {
//                         Id = it.key!!
//                         Name = it.child("Name").value.toString()
//                         FirstName = it.child("FirstName").value.toString()
//                         LastName = it.child("LastName").value.toString()
//                         ImageURL = it.child("ImageURL").value.toString()
//                         CreatedAt = it.child("CreatedAt").value.toString()
//                         EditedAt = it.child("EditedAt").value.toString()
//                     }
//                     buf.add(user)
//                     done.set(true)
//                 }
//             }
//
//             override fun onCancelled(p0: DatabaseError) {
//                 TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//             }
//         })
//
//         while (!done.get()){Log.d("Firebase", "Waiting")}
//         Log.d("Firebase","Here")
//         users.forEach {
//             Log.d("Firebase", "${it.FirstName} ")
//         }
//    }
//
//    fun LogIn(userName: String, password: String) {
//        val data = db.orderByChild("Name").equalTo(userName)
//        data.addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun  onDataChange(snapshot: DataSnapshot) {
////                return snapshot.exists() && snapshot.children.iterator().next().child("Password").value.toString() == password
////                val buf = ArrayList<UserModel>()
////                data.forEach {
////                    val user = UserModel()
////                    with (user) {
////                        Id = it.key!!
////                        Name = it.child("Name").value.toString()
////                        FirstName = it.child("FirstName").value.toString()
////                        LastName = it.child("LastName").value.toString()
////                        ImageURL = it.child("ImageURL").value.toString()
////                        CreatedAt = it.child("CreatedAt").value.toString()
////                        EditedAt = it.child("EditedAt").value.toString()
////                    }
////                    buf.add(user)
//                }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//            })
//
//    }
}