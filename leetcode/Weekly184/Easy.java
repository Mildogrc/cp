class Easy {
    public List<String> stringMatching(String[] words) {
        List<String> ret = new ArrayList<>();
        o:for(int i = 0; i<words.length; i++){
            for(int j = 0; j<words.length; j++){
                if(i != j){
                    if(words[j].contains(words[i])){
                        ret.add(words[i]);
                        continue o;
                    }
                }
            }
        }
        return ret;
    }
}
