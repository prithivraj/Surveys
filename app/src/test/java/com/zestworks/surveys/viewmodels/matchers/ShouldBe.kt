@file:Suppress("UNCHECKED_CAST")

package com.zestworks.surveys.viewmodels.matchers

infix fun <T> T.shouldBe(any: Any?) = shouldEqual(any)

infix fun <T> T.shouldEqual(any: Any?) {
    when (any) {
        is ShouldBePojo<*> -> should(any as ShouldBePojo<T>)
        else -> {
            if (this == null && any != null)
                throw equalsError(any, this)
            if (this != any)
                throw equalsError(any, this)
        }
    }
}

infix fun <T> T.should(shouldBePojo: ShouldBePojo<T>) {
    val result = shouldBePojo.test(this)
    if (!result.passed)
        throw AssertionError(result.message)
}

interface ShouldBePojo<T> {
    fun test(value: T): Result
}

data class Result(val passed: Boolean, val message: String)

private fun equalsError(expected: Any?, actual: Any?) = AssertionError(equalsErrorMessage(expected, actual))

private fun equalsErrorMessage(expected: Any?, actual: Any?) = "should be: $expected but the value is: $actual"