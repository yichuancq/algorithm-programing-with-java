package com.example.algorithm.merkleTree;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yichuan
 * @version 1.0
 * @description: 默克尔树
 * @date 2024/4/7 1:13
 */
public class MerkleTree {

    private static final String HASH_ALGORITHM = "SHA-256";
    private MerkleNode rootNode;

    public MerkleTree(List<String> dataList) throws NoSuchAlgorithmException {
        rootNode = buildMerkleTree(dataList);
    }

    /**
     * 构建树
     *
     * @param dataList
     * @return
     * @throws NoSuchAlgorithmException
     */
    private MerkleNode buildMerkleTree(List<String> dataList) throws NoSuchAlgorithmException {
        List<MerkleNode> nodes = new ArrayList<>();
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }

        for (String data : dataList) {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            nodes.add(new MerkleNode(null, null, hash));
        }

        while (nodes.size() > 1) {
            if (nodes.size() % 2 != 0) {
                // if odd number of nodes, duplicate the last one
                nodes.add(nodes.get(nodes.size() - 1));
            }

            List<MerkleNode> newLevel = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i += 2) {
                MerkleNode left = nodes.get(i);
                MerkleNode right = nodes.get(i + 1);
                MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
                digest.update(left.getData());
                digest.update(right.getData());
                byte[] hash = digest.digest();
                newLevel.add(new MerkleNode(left, right, hash));
            }

            nodes = newLevel;
        }
        // root node
        return nodes.get(0);
    }

    public byte[] getRootHash() {
        return rootNode.getData();
    }

    /**
     * @param hash
     * @return
     */
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 这个例子中，MerkleTree 类表示默克尔树，它有一个根节点 rootNode。MerkleNode 是内部类，表示树中的节点。每个节点都有一个左子节点
     * 、一个右子节点和一个数据字段（这里是哈希值的字节数组）。buildMerkleTree 方法根据输入的数据列表构建默克尔树，并返回根节点。
     * getRootHash方法返回根节点的哈希值。在 main 方法中，我们创建了一个包含四个字符串的列表，并用它来构建一个默克尔树。
     * 然后，我们打印出根哈希值的十六进制表示。
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<String> dataList = Arrays.asList("data1", "data2", "data3", "data4");
        MerkleTree merkleTree = new MerkleTree(dataList);
        byte[] rootHash = merkleTree.getRootHash();
        System.out.println("Root Hash: " + bytesToHex(rootHash));
    }


}
