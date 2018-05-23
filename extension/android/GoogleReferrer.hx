package extension.android;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end

#if (android && openfl)
import openfl.utils.JNI;
#end

typedef ReferrerDetails =
{
	var referrerURL:String;
	var beginInstallTS:Int; // sec
	var beginClickTS:Int;	// sec
}


class GoogleReferrer {

	private static var instance:GoogleReferrer;

	#if ios

		private static var extension_google_referrer_sample_method = Lib.load ("extension_google_referrer", "extension_google_referrer_sample_method", 1);

	#elseif (android && openfl)

		public static var onSuccess_jni:Dynamic;
		public static var onError_jni:String -> Void;
		private static var extension_google_referrer_addCallback_jni = JNI.createStaticMethod ("org.haxe.extension.Extension_google_referrer", "addCallback", "(ILorg/haxe/lime/HaxeObject;)V");

	#end

	private function new()
	{}

	public static function getInstance():GoogleReferrer
	{
		if (instance == null)
		{
			instance = new GoogleReferrer();
		}

		return instance;
	}

	public static function addCallback(onSuccess:ReferrerDetails -> Void, onError:String -> Void):Void {
		
		#if (android && openfl)
		
		onSuccess_jni = onSuccessWrapper.bind(onSuccess);
		onError_jni = onError;
		extension_google_referrer_addCallback_jni(getInstance());
		
		#else
		
		extension_google_referrer_sample_method(inputValue);
		
		#end
		
	}

	private static function onSuccessWrapper(onSuccess:ReferrerDetails -> Void, referrerURL:String, installTs:Int, clickTs:Int):Void
	{
		onSuccess(cast {referrerURL:referrerURL, beginInstallTS:installTs, beginClickTS:clickTs});
	}

	
	
}