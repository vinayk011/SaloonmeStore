/*
 *  Created by Vinay on 22-1-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.listener


import android.widget.ImageView
import android.widget.TextView
import com.ezeetech.saloonme.store.model.UserBlog

interface UserBlogActionListener {
    fun onReadMoreClicked(userBlog: UserBlog, imageView: ImageView, textView: TextView)
}