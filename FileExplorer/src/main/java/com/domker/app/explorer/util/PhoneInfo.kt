package com.domker.app.explorer.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.telephony.TelephonyManager
import android.text.format.Formatter
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.regex.Pattern


/**
 * Created by wanlipeng on 2017/9/2.
 */
class PhoneInfo constructor(context: Context) {
    private val NOT_AVAILABLE = "not available"
    private val mContext = context
    private val mTelephonyManager = mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    /**
     * 得到应用的堆栈信息，返回单位是kb
     *
     * @return int
     */
    fun getAppMaxHeapSize(): Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    fun getAppTotalHeapSize() = (Runtime.getRuntime().totalMemory() / 1024).toInt()

    fun getAppFreeHeapSize() = (Runtime.getRuntime().freeMemory() / 1024).toInt()

    @SuppressLint("MissingPermission")
    fun getImei(): String = mTelephonyManager.deviceId ?: NOT_AVAILABLE

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.M)
    fun getImei(index: Int): String {
        return mTelephonyManager.getDeviceId(index) ?: return NOT_AVAILABLE
    }

    @SuppressLint("MissingPermission")
    fun getMacAddress(): String {
        val wifi = mContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifi.connectionInfo
        return if (info != null && info.macAddress != null) {
            info.macAddress
        } else NOT_AVAILABLE
    }

    fun getAndroidID(): String = Settings.Secure.getString(mContext.contentResolver, Settings.Secure.ANDROID_ID)

    @SuppressLint("MissingPermission")
    fun getImsi(): String {
        val imsi = mTelephonyManager.subscriberId
        return imsi ?: NOT_AVAILABLE
    }

    /**
     * 系统sdk版本号
     * @return String
     */
    fun getSdkVersion(): Int = Build.VERSION.SDK_INT

    /**
     * 系统Build的信息
     */
    fun getOsBuildInfo(): List<String> {
        val data = ArrayList<String>()
        data.add("SERIAL: " + Build.SERIAL)
        data.add("Product: " + Build.PRODUCT)
        data.add("Bootloader: " + Build.BOOTLOADER)
        data.add("CPU_ABI: " + getAbis())
        data.add("TAGS: " + Build.TAGS)
        data.add("VERSION_CODES.BASE: " + Build.VERSION_CODES.BASE)
        data.add("MODEL: " + Build.MODEL)
        data.add("SDK: " + Build.VERSION.SDK)
        data.add("VERSION.RELEASE: " + Build.VERSION.RELEASE)
        data.add("DEVICE: " + Build.DEVICE)
        data.add("DISPLAY: " + Build.DISPLAY)
        data.add("BRAND: " + Build.BRAND)
        data.add("BOARD: " + Build.BOARD)
        data.add("FINGERPRINT: " + Build.FINGERPRINT)
        data.add("ID: " + Build.ID)
        data.add("MANUFACTURER: " + Build.MANUFACTURER)
        data.add("USER: " + Build.USER)
        return data
    }

    fun getAbis(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var ab = ""
            for (abis in Build.SUPPORTED_ABIS) {
                ab += abis + " "
            }
            ab
        } else {
            Build.CPU_ABI
        }
    }

    /**
     * 得到屏幕分辨率
     *
     * @param activity
     * @return String
     */
    fun getScreenSize(activity: Activity): String {
        val width = activity.windowManager.defaultDisplay.width
        val height = activity.windowManager.defaultDisplay.height
        return String.format("%d * %d", width, height)
    }

    fun getDensityDpi() = mContext.resources.displayMetrics.densityDpi

    fun getDensity() = mContext.resources.displayMetrics.density

    fun getDpiDensity(): String {
        val dm = mContext.resources.displayMetrics
        return String.format("%f * %f", dm.xdpi, dm.ydpi)
    }

    /**
     * 获取android当前可用内存大小,将获取的内存大小规格化
     */
    fun getAvailMemory(): String {
        val am = mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()
        am.getMemoryInfo(mi)
        return Formatter.formatFileSize(mContext, mi.availMem)
    }


    /**
     * 系统内存信息文件
     * 读取meminfo第一行，系统总内存大小
     * 获得系统总内存，单位是KB，乘以1024转换为Byte
     * Byte转换为KB或者MB，内存大小规格化
     */
    fun getTotalMemory(): String {
        val str1 = "/proc/meminfo"
        val str2: String
        var arrayOfString: String? = null
        var initial_memory: Long = 0
        try {
            val localFileReader = FileReader(str1)
            val localBufferedReader = BufferedReader(localFileReader, 8192)
            str2 = localBufferedReader.readLine()
            val matcher = Pattern.compile("\\d+").matcher(str2)
            if (matcher.find()) {
                arrayOfString = matcher.group()
            }
            initial_memory = java.lang.Long.valueOf(arrayOfString)!!.toLong() * 1024
            localBufferedReader.close()
        } catch (e: IOException) {
        }
        return Formatter.formatFileSize(mContext, initial_memory)
    }

    companion object {
        /**
         * 获取sd卡路径
         */
        fun getSdCardPath(): String? {
            return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                Environment.getExternalStorageDirectory().toString()
            } else null
        }
    }
}
