# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\developtools\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# keep 保留，例如keepattributes：表示保留属性
# dont 不要，例如dontwarn：表示不要提示警告
# ignore 忽略，例如ignorewarning：表示忽略警告


-optimizationpasses 5
-dontshrink
-dontoptimize
#保留行号
-keepattributes SourceFile,LineNumberTable

# 防止反射失败，使用案例友盟、retrofit，保持泛型
-keepattributes *Annotation*
-keepattributes Exceptions,InnerClasses,Signature
#反射
-keepattributes EnclosingMethod
-keep public class com.test.cheng.practice.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

#android-support-v4.jar依赖包
-dontwarn android-support-v4.**
-keep class android.support.v4.** {*;}
-keep interface android.support.v4.app.**{ *;}
-keep public class javax.**
-keep public class android.webkit.**

#不混淆Parcelable的子类，防止android.os.BadParcelableException
#-keep class * implements android.os.Parcelable {
#public static final android.os.Parcelable$Creator*;
#}

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}


#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

#retrofit
-dontwarn rx.**
-keep class rx.** { *; }
-dontwarn okio.**

-dontwarn com.squareup.**
-keep class com.squareup.** { *; }
-keep interface com.squareup.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn retrofit2.Platform$Java8


-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-keep class android.net.http.** { *; }
-dontwarn android.net.http.**
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

-dontwarn com.roughike.**