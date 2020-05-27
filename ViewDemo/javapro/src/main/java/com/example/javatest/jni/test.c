#include "com_example_JniTest.h"
#include<stdio.h>

JNIEXPORT jstring JNICALL Java_com_example_JniTest_get(JNIEnv *env, jobject thiz){
    printf("invoke get from c\n");
    return (*env)->NewStringUTF(env,"Hello from jni!");
}

/*
 * Class:     com_example_JniTest
 * Method:    set
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_JniTest_set(JNIEnv *rnv, jobject thiz, jstring string){
    printf("invoke set from c\n");
    char* str = (char*) (*env)->GetStringUTFChars(env,string,null);
    printf("%s",str);
    (*env)->ReleaseStringUTFChars(env,string,str);

}