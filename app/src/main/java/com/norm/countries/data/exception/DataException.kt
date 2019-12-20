package com.norm.countries.data.exception

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
class DataException(
    val issue: Issue
): Exception() {
    fun isIssue(issue: Issue): Boolean {
        return this.issue === issue
    }

    fun shouldRetry(): Boolean {
        return this.issue.shouldRetry()
    }
}