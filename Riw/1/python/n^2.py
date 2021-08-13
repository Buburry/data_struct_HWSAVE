arr = [10, 2, 1, 5, 7, 8]

def bubbleSort(arr):
    for i in range(len(arr)):
        print(len(arr)-i-1)
        for j in range(0, len(arr)-i-1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    
    return arr

print(bubbleSort(arr))