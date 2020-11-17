public class Rank {

    private String key;
    private Integer value;

    public Rank(String key){
        determineRank(key);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private void determineRank(String key){
        this.value = Rankings.getRanking().get(key).intValue();
    }
}