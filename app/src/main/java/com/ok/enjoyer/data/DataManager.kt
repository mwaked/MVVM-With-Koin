package com.ok.enjoyer.data

import com.ok.enjoyer.data.local.prefs.PrefsManager
import com.ok.enjoyer.data.remote.api.ApiManager

interface DataManager : ApiManager, PrefsManager