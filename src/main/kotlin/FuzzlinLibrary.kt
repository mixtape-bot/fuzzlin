package gg.mixtape.fuzzlin

import gg.mixtape.natives.loader.NativeLibraryLoader

internal class FuzzlinLibrary private constructor() {
    companion object {
        private val nativeLoader = NativeLibraryLoader.create("fuzzlin", FuzzlinLibrary::class.java)

        fun createInstance(): FuzzlinLibrary {
            nativeLoader.load()
            return FuzzlinLibrary()
        }
    }

    /* ratio */
    external fun ratio(s1: String, s2: String, scoreCutoff: Int): Int
    external fun partialRatio(s1: String, s2: String, scoreCutoff: Int): Int

    /* token sort ratio */
    external fun tokenSortRatio(s1: String, s2: String, scoreCutoff: Int): Int
    external fun partialTokenSortRatio(s1: String, s2: String, scoreCutoff: Int): Int

    /* token set ratio */
    external fun tokenSetRatio(s1: String, s2: String, scoreCutoff: Int): Int
    external fun partialTokenSetRatio(s1: String, s2: String, scoreCutoff: Int): Int
    
    /* weighted ratio */
    external fun weightedRatio(s1: String, s2: String, scoreCutoff: Int): Int

    /* quick ratio */
    external fun quickRatio(s1: String, s2: String, scoreCutoff: Int): Int
}
