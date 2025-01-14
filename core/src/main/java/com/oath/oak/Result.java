package com.oath.oak;

import static com.oath.oak.ValueUtils.ValueResult.SUCCESS;

/**
 * A sum type for holding either a generic type value or a boolean flag.
 */
class Result<V> {
    // This value holds whether the value succeeded or not, and whether the relevant value was moved.
    final ValueUtils.ValueResult operationResult;
    // In case of success, the hasValue flag is set, and the value is written in value.
    final V value;              // stored value

    static <V> Result<V> withValue(V value) {
        return new Result<>(SUCCESS, value);
    }

    static <V> Result<V> withFlag(ValueUtils.ValueResult flag) {
        return new Result<>(flag, null);
    }

    private Result(ValueUtils.ValueResult operationResult, V value) {
        this.operationResult = operationResult;
        this.value = value;
    }
}
