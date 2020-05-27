class Easy {
    public String reformat(String s) {
        List<Character> nums = new ArrayList<>();
        List<Character> letter = new ArrayList<>();
        for(char x : s.toCharArray()){
            if(Character.isDigit(x)){
                nums.add(x);
            }else{
                letter.add(x);
            }
        }
        StringBuilder sb = new StringBuilder();
        int n = Math.min(nums.size(), letter.size());
        int max = Math.max(nums.size(), letter.size());
        if(letter.size() < 2 && nums.size()  < 2){
            StringBuilder sb1 = new StringBuilder();
            try{
                sb1.append(letter.get(0));
            }catch(Exception e){}
            try{
                sb1.append(nums.get(0));
            }catch(Exception e){}
            return sb1.toString();
        }
        if(n==0) return "";
        for(int i = 0; i<n; i++){
            sb.append(nums.get(i));
            sb.append(letter.get(i));
        }
        if(nums.size() == letter.size()) return sb.toString();
        if(nums.size()>n){
            if(Character.isDigit(sb.charAt(0))){
                sb.append(nums.get(n));
            }else{
                sb.insert(0, nums.get(n));
            }
        }else{
            if(Character.isDigit(sb.charAt(0))){
                sb.insert(0, letter.get(n));
            }else{
                sb.append(letter.get(n));
            }
        }
        return sb.toString();
    }
}
