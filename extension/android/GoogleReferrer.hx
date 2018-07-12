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

		public var onSuccess_jni:String -> Int -> Int -> Void;
		public var onError_jni:String -> Void;
		private static var extension_google_referrer_addCallback_jni = JNI.createStaticMethod ("org.haxe.extension.GoogleReferrer", "addCallback", "(Lorg/haxe/lime/HaxeObject;)V");

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
		
		getInstance().onSuccess_jni = onSuccessWrapper.bind(onSuccess);
		getInstance().onError_jni = onError;
		extension_google_referrer_addCallback_jni(getInstance());
		
		#else
		
		extension_google_referrer_sample_method(inputValue);
		
		#end
		
	}

	private static function onSuccessWrapper(onSuccess:ReferrerDetails -> Void, referrerURL:String, installTs:Int, clickTs:Int):Void
	{
		var result = {referrerURL:referrerURL, beginInstallTS:installTs, beginClickTS:clickTs};
		onSuccess(result);
	}
}