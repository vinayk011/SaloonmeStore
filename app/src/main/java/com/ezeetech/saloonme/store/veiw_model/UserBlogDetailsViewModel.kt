/*
 *  Created by Vinay on 2-2-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

package com.ezeetech.saloonme.store.veiw_model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ezeetech.saloonme.store.model.UserBlog

class UserBlogDetailsViewModel : ViewModel() {
    val userBlog = ObservableField<UserBlog>()
}