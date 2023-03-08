package bean;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/8 21:00
 */
public class TrieNode {

    public int pass;

    public int end;

    public TrieNode[] nexts;

    public TrieNode(){
        pass = 0;
        end = 0;
        nexts = new TrieNode[26];
    }
}
