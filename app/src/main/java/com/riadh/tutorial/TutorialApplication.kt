package com.riadh.tutorial

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TutorialApplication @Inject constructor() : Application()