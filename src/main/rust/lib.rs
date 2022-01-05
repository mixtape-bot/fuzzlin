use fuzzywuzzy::fuzz;
use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::jint;

fn java_string_to_rust_string(jni: JNIEnv, java: JString) -> String {
    jni.get_string(java).unwrap().into()
}

fn do_ratio(env: JNIEnv, s1: JString, s2: JString, score_cutoff: jint, ratio_type: i16) -> jint {
    let a = &java_string_to_rust_string(env,s1);
    let b = &java_string_to_rust_string(env,s2);

    let score = match ratio_type {
        /* ratio */
        0 => fuzz::ratio(a, b),
        1 => fuzz::partial_ratio(a, b),
        /* token sort ratio */
        2 => fuzz::token_sort_ratio(a, b, true, true),
        3 => fuzz::partial_token_sort_ratio(a, b, true, true),
        /* token set ratio */
        4 => fuzz::token_set_ratio(a, b, true, true),
        5 => fuzz::partial_token_set_ratio(a, b, true, true),
        /* weighted */
        8 => fuzz::wratio(a, b, true, true),
        /* quick */
        9 => fuzz::qratio(a, b, true),
        _ => panic!("unknown ratio type.")
    };

    return if score < score_cutoff as u8 { 0 } else { score } as jint;
}

/* ratio */
#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_ratio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 0)
}

#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_partialRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 1)
}

/* token sort ratio */
#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_tokenSortRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 2)
}

#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_partialTokenSortRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 3)
}

/* token set ratio */
#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_tokenSetRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 4)
}

#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_partialTokenSetRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 5)
}

/* weighted ratio */
#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_weightedRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 8)
}

/* quick ratio */
#[no_mangle]
pub extern "system" fn Java_gg_mixtape_fuzzlin_FuzzlinLibrary_quickRatio(jni: JNIEnv, _: JClass, s1: JString, s2: JString, score_cutoff: jint) -> jint {
    do_ratio(jni, s1, s2, score_cutoff, 9)
}
