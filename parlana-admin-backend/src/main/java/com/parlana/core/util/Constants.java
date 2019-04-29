package com.parlana.core.util;

public class Constants {
	
//  Make google bucket readable to download audio files	
//	$ ./gsutil iam ch allUsers:objectViewer gs://mirkos-bucket

//	 //Local
//	 public final static String GCLOD_PATH = "/home/mirko/Programs/google-cloud-sdk/bin/";
//	 public final static String GCLOD_TOKEN_JSON = "/home/mirko/eclipse-workspace/TestProject/resources/SpeechRecognitionProject.json";
//	 public final static String GCLOD_SHELL = "./obtainGoogleToken.sh";
//	 public final static String HOME_RESOURCES_FILES = "/home/mirko/eclipse-workspace/parlana-admin-backend/src/main/resources/files/";

	// EC2
	public final static String GCLOD_PATH = "/home/ec2-user/google/google-cloud-sdk/bin/";
	public final static String GCLOD_TOKEN_JSON = "/home/ec2-user/google/google-cloud-sdk/bin/SpeechRecognitionProject.json";
	public final static String GCLOD_SHELL = "./obtainGoogleToken.sh";
	public final static String HOME_RESOURCES_FILES = "/home/ec2-user/files/";
	
	
	
	

	public final static String KEY = "@vay@";

	public final static String GOOGLE_APPLICATION_CREDENTIALS = "GOOGLE_APPLICATION_CREDENTIALS";
	public final static String HOME_RESOURCES_WATSON = HOME_RESOURCES_FILES + "watson/";
	public final static String HOME_RESOURCES_GOOGLE = HOME_RESOURCES_FILES + "google/";

	public final static String INTENT_DEFAULT = "INTENT_DEFAULT";

	public final static String LANG_BASE = "es";
	
	// Google Cloud
	public final static String GOOGLE_SPEECH_API_URI = "https://speech.googleapis.com/v1/speech:recognize";
	public final static String GOOGLE_STORAGE_URI = "https://storage.googleapis.com/mirkos-bucket/";
	public final static String GOOGLE_STORAGE_GS_URI = "gs://mirkos-bucket/";

	// Watson Conversation
	public final static String WATSON_CONV_WORKSPACE_ID = "c63c58b3-27f6-4f05-a48d-376371974af9";
	public final static String WATSON_CONV_ENDPOINT = "https://gateway.watsonplatform.net/conversation/api/v1/workspaces/" + WATSON_CONV_WORKSPACE_ID + "/message?version=2017-05-26";
	public final static String WATSON_CONV_USERNAME = "a9ef5682-f265-4e11-972a-058fee624166";
	public final static String WATSON_CONV_PASSWORD = "QNHJWrnm7lVH";

	// Watson NLU
	public final static String WATSON_NLU_ENDPOINT = "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27";
	public final static String WATSON_NLU_USERNAME = "9e36a936-f0c7-4975-9a17-2b382a28dfd2";
	public final static String WATSON_NLU_PASSWORD = "NuJGNfyV5Xa7";

	// Watson Translator
	public final static String WATSON_TRANSLATOR_ENDPOINT = "https://gateway.watsonplatform.net/language-translator/api/v2/translate";
	public final static String WATSON_TRANSLATOR_USERNAME = "0770bcea-72eb-431d-b174-2e9c98cb0f2a";
	public final static String WATSON_TRANSLATOR_PASSWORD = "Oq3wuoXNxGyo";

}
