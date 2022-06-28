function solution(){
    var answer = 0;
    var arr3 = [[],[]];
    
    for(i=0; i<arr1.length; i++){
        for(j=0; j<arr1.length; j++){
            arr3[i][j]=arr1[i][j]+arr2[i][j]
        }
    }
    return arr3;
}
var arr1 = [[1,2],[2,3]]
var arr2 = [[3,4],[5,6]]
var b = solution();
document.write(b);