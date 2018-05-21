package extension.android;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end

#if (android && openfl)
import openfl.utils.JNI;
#end


class GoogleReferrer {
	
	
	public static function sampleMethod (inputValue:Int):Int {
		
		#if (android && openfl)
		
		var resultJNI = extension_google_referrer_sample_method_jni(inputValue);
		var resultNative = extension_google_referrer_sample_method(inputValue);
		
		if (resultJNI != resultNative) {
			
			throw "Fuzzy math!";
			
		}
		
		return resultNative;
		
		#else
		
		return extension_google_referrer_sample_method(inputValue);
		
		#end
		
	}
	
	
	private static var extension_google_referrer_sample_method = Lib.load ("extension_google_referrer", "extension_google_referrer_sample_method", 1);
	
	#if (android && openfl)
	private static var extension_google_referrer_sample_method_jni = JNI.createStaticMethod ("org.haxe.extension.Extension_google_referrer", "sampleMethod", "(I)I");
	#end
	
	
}