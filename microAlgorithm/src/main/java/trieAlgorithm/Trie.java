package trieAlgorithm;

import bean.TrieNode;

import javax.swing.tree.TreeNode;
import java.util.TreeMap;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/8 21:03
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void delete(String word){
        if (search(word) != 0){
            char[] chars = word.toCharArray();
            int index = 0;

            TrieNode node = root;

            for (char ch : chars){
                index = ch - 'a';
                if (--node.nexts[index].pass == 0){
                    node.nexts[index] = null;
                    return;
                }

                node = node.nexts[index];
            }

            node.end--;
        }
    }

    public void insert(String word){
        if (word == null){
            return;
        }

        char[] chars = word.toCharArray();
        int index = 0;
        TrieNode node = root;

        for (char ch : chars){
            index = ch - 'a';
            if (node.nexts[index] == null){
                node.nexts[index] = new TrieNode();
            }

            node = node.nexts[index];
            node.pass++;
        }

        node.end++;
    }

    public int search(String word){
        if (word == null){
            return 0;
        }

        char[] chars = word.toCharArray();
        int index = 0;

        TrieNode node = root;

        for (char ch : chars){
            index = ch - 'a';
            if (node.nexts[index] == null){
                return 0;
            }

            node = node.nexts[index];
        }

        return node.pass;
    }
}
