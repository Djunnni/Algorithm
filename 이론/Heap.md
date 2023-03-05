## 힙

### 힙의 특징

* heapify : O(logN)
* 처음 힙 생성 : O(N)
* 특정위치 삽입, 삭제: O(logN)
* 맨 위(최소, 최대)의 값에 접근이 가능하며 특정원소에 랜덤 접근은 어렵다. 
* 맨위요소에 대해서는 알 수 있으나 하위요소에 대해 어떻게 정렬됐는지는 순서에 따라 다르다.


###  최대힙, 최소힙

Java에서는 PriorityQueue, ArrayDeque를 사용해서 구현하기

### heapify 예시 코드

```javascript
// 최대힙을 구하는 heapify라고 하자
function(arr[], n, i) {
    let largest = i;
    let l = 2*i;
    let r = 2*i + 1;

    if(l <= n && arr[largest] < arr[l]) {
        largest = l;
    }

    if(r <= n && arr[largest] < arr[r]) {
        largest = r;
    }

    if(largest != i) {
        swap(arr[largest], arr[i]);
        heapify(arr, n, largest); // 변경된 largest 위치부터 다시 타고 내려간다.
    }
}
```


### INSERT

```javascript
// 최대 힙을 구할 때,
function insert(arr[], n, item) {
    arr.append(item);

    let i = n + 1;
    while(i > 1 && arr[i / 2] < arr[i]) {
        swap(arr[i / 2], arr[i]);
        i = i / 2;
    }
}

```


### REMOVE

```javascript
function remove(arr[], n) {
    arr[1] = arr[n];
    heapify(arr, n - 1, 1);
}

```