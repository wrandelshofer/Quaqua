/*
 * @(#)ch_randelshofer_quaqua_osx_OSXClipboardTransferable.m
 * Quaqua Look and Feel. Copyright 2020 (c) Werner Randelshofer, Switzerland. MIT License.
 */

/**
 * Native code for class ch.randelshofer.quaqua.osx.OSXClipboardTransferable.
 *
 * @version $Id$
 */

#include <jni.h>
#include <stdio.h>
#include "ch_randelshofer_quaqua_osx_OSXClipboardTransferable.h"
#import <Cocoa/Cocoa.h>
#import <CoreServices/CoreServices.h>

/*
 * Related documentation:
 * ----------------------
 * NSPasteboard Class Reference
 * http://developer.apple.com/documentation/Cocoa/Reference/ApplicationKit/Classes/NSPasteboard_Class/Reference/Reference.html#//apple_ref/occ/instm/NSPasteboard/dataForType:
 */


/*
 * Class:     ch_randelshofer_quaqua_osx_OSXClipboardTransferable
 * Method:    getTypes
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_ch_randelshofer_quaqua_osx_OSXClipboardTransferable_nativeGetTypes
  (JNIEnv *env, jclass javaClass) {

    jobjectArray typesJ = NULL;

    // Allocate a memory pool
    NSAutoreleasePool* pool = [NSAutoreleasePool new];

    // Get the NSPasteboard
    NSPasteboard *pb = [NSPasteboard generalPasteboard];

    if (pb != nil) {
        NSArray *types = [pb types];
        if (types != nil) {
            typesJ = (*env)->NewObjectArray(
                        env,
                       (jsize) [types count],
                        (*env)->FindClass(env, "java/lang/String"),
                        NULL
                    );

            int len = (int) [types count];
            int i;
            for (i=0; i < len; i++) {
                NSString *typeNameNS = [types objectAtIndex: i];
                if (typeNameNS != nil) {
                    // Convert NSString to jstring
                    jstring typeNameJ = (*env)->NewStringUTF(env, [typeNameNS UTF8String]);

                    // Store in array
                    (*env)->SetObjectArrayElement(env, typesJ, i, typeNameJ);
                }
            }
        }
    }

    // Release memory pool
    [pool release];

    return typesJ;
}

/*
 * Class:     ch_randelshofer_quaqua_osx_OSXClipboardTransferable
 * Method:    getDataForType
 * Signature: (Ljava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_ch_randelshofer_quaqua_osx_OSXClipboardTransferable_nativeGetDataForType
  (JNIEnv *env, jclass javaClass, jstring typeJ) {

    jbyteArray dataJ = NULL;

    // Allocate a memory pool
    NSAutoreleasePool* pool = [[NSAutoreleasePool alloc] init];

    // Get the NSPasteboard
    NSPasteboard *pb = [NSPasteboard generalPasteboard];
    if (pb != nil) {
        // Convert Java String to NS String
        const jchar *typeC = (*env)->GetStringChars(env, typeJ, NULL);
        NSString *typeNS = [NSString stringWithCharacters:(UniChar *)typeC
            length:(*env)->GetStringLength(env, typeJ)];
        (*env)->ReleaseStringChars(env, typeJ, typeC);

        // Get the data
        NSData *dataNS = [pb dataForType: typeNS];
        if (dataNS != nil) {

            // Copy data into Java byte array
            int len = (int) [dataNS length];
            void* dataC = malloc(len);
            [dataNS getBytes:dataC length:len];
            dataJ = (*env)->NewByteArray(env, len);
            (*env)->SetByteArrayRegion(env, dataJ, 0, len, (jbyte*)dataC);
            free(dataC);
        }
    }

    // Release memory pool
    [pool release];

    return dataJ;
}

/*
 * Class:     ch_randelshofer_quaqua_osx_OSXClipboardTransferable
 * Method:    getNativeCodeVersion
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_ch_randelshofer_quaqua_osx_OSXClipboardTransferable_nativeGetNativeCodeVersion
  (JNIEnv *env, jclass javaClass) {

    return 2;
}



/*JNI function definitions end*/
