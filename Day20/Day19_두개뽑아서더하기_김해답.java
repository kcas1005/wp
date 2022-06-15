import java.util.ArrayList;
import java.util.Collections;
public class Day19_두개뽑아서더하기_김해답{
    
    public static void main(String[] args){
        int numbers[] = {2,1,3,4,1};
        solutionB.solution(numbers);
    }
}

class solutionB{
    public static int[] solution(int[] numbers) {
        
        int sum = 0;
        
    //  const = 변하지 않음! : 개발자가 변하지말라고 건들지말라고
         ArrayList<Integer> sumList = new ArrayList<Integer>();
        
        for(int i =0; i<numbers.length-1; i++){
            for(int j =i+1; j<numbers.length; j++){
                sum = numbers[i] + numbers[j];
                if(!sumList.contains(sum)){ 
                    sumList.add(sum);
                   
                }
                    
            }
          
        }
        
        Collections.sort(sumList);
        System.out.println(sumList);
  
        int[] answer = new int[sumList.size()];
        for(int i = 0; i<answer.length; i++){
          answer[i] = sumList.get(i);
        }
        System.out.println("이건 병진씨 메소드에서 나온거다.");
        System.out.println("answer: " + answer.getClass().getName());
        return answer;
    }
}