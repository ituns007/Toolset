# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontoptimize
-keeppackagenames org.ituns.android.toolset

-keep class org.ituns.android.toolset.** { *; }

-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.Observer { *; }
-keep class androidx.lifecycle.LifecycleOwner { *; }
-keep class androidx.lifecycle.LiveData$ObserverWrapper { *; }
-keep class androidx.lifecycle.LiveData$AlwaysActiveObserver { *; }
-keep class androidx.lifecycle.LiveData$LifecycleBoundObserver { *; }
-keep class androidx.arch.core.internal.SafeIterableMap { *; }
-keep class androidx.arch.core.internal.SafeIterableMap$SupportRemove { *; }
-keep class androidx.arch.core.internal.SafeIterableMap$IteratorWithAdditions { *; }

-keep class org.ituns.android.toolset.R{ *; }
-keep class org.ituns.android.toolset.R$* { public static <fields>; }