package gg.mixtape.fuzzlin

public object Fuzzlin {
    private val library: FuzzlinLibrary by lazy { FuzzlinLibrary.createInstance() }

    /**
     * Calculates a simple ratio between two strings.
     *
     * ```kt
     * // score is 96.55
     * val score = Fuzzlin.ratio("this is a test", "this is a test!")
     * ```
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun ratio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.ratio(s1, s2, scoreCutoff)

    /**
     * Calculates the [ratio] of the optimal string alignment.
     *
     * ```kt
     * // score is 100
     * val score = Fuzzlin.partialRatio("this is a test", "this is a test!")
     * ```
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun partialRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.partialRatio(s1, s2, scoreCutoff)

    /**
     * Sorts the words in the strings and calculates the [ratio] between them.
     *
     * ```kotlin
     * // score is 100
     * val score = Fuzzlin.tokenSortRatio("fuzzy wuzzy was a bear", "wuzzy fuzzy was a bear")
     * ```
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun tokenSortRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.tokenSortRatio(s1, s2, scoreCutoff)

    /**
     * Sorts the words in the strings and calculates the [partialRatio] between them.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun partialTokenSortRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.partialTokenSortRatio(s1, s2, scoreCutoff)

    /**
     * Compares the words in the strings based on unique and common words between them using [ratio].
     *
     * ## Example
     *
     * ```kt
     * // score1 is 83.87
     * val score1 = Fuzzlin.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")
     *
     * // score2 is 100
     * val score2 = Fuzzlin.tokenSetRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")
     * ```
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun tokenSetRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.tokenSetRatio(s1, s2, scoreCutoff)

    /**
     * Compares the words in the strings based on unique and common words between them using [partialRatio].
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun partialTokenSetRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.partialTokenSetRatio(s1, s2, scoreCutoff)

    /**
     * Calculates a weighted ratio based on the other ratio algorithms
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun weightedRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.weightedRatio(s1, s2, scoreCutoff)

    /**
     * Calculates a quick ratio between two strings using [ratio].
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     * returned.
     *
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public fun quickRatio(s1: String, s2: String, scoreCutoff: Int = 0): Int =
        library.quickRatio(s1, s2, scoreCutoff)
}
