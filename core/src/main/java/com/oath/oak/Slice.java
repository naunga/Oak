/*
 * Copyright 2018 Oath Inc.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms.
 */

package com.oath.oak;

import java.nio.ByteBuffer;

import static com.oath.oak.ValueUtils.LockStates.FREE;

// Slice is a "small part" of a bigger block of the underlying managed memory.
// Slice is allocated for data (key or value) and can be de-allocated later
public class Slice {
    private final int blockID;
    private final ByteBuffer buffer;
    // This field is only used for sanity checks purposes and it should not be used, nor changed.
    private final int originalPosition;

    public Slice(int blockID, ByteBuffer buffer) {
        this.blockID = blockID;
        this.buffer = buffer;
        this.originalPosition = buffer.position();
    }

    Slice(int blockID, int position, int length, MemoryManager memoryManager) {
        this(blockID, memoryManager.getByteBufferFromBlockID(blockID, position, length));
    }

    Slice duplicate() {
        return new Slice(blockID, buffer.duplicate());
    }

    public ByteBuffer getByteBuffer() {
        return buffer;
    }

    int getBlockID() {
        return blockID;
    }

    void initHeader() {
        buffer.putInt(buffer.position(), FREE.value);
    }

    boolean validatePosition() {
        return originalPosition == buffer.position();
    }
}
