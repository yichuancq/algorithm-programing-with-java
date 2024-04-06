package com.example.algorithm.merkleTree;

/**
 * @author yichuan
 * @version 1.0
 * @description: MerkleNode
 *
 *
 * @date 2024/4/7 1:16
 */
public class MerkleNode {
    private MerkleNode left;
    private MerkleNode right;
    private byte[] data;

    MerkleNode(MerkleNode left, MerkleNode right, byte[] data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public MerkleNode getLeft() {
        return left;
    }

    public MerkleNode getRight() {
        return right;
    }

    public byte[] getData() {
        return data;
    }
}
