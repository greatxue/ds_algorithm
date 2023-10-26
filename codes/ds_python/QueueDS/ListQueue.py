from QueueDS import QueueDS


class ListQueueDS(QueueDS):
    """ implementation of a queue using a list"""
    default_capacity = 5

    def __init__(self):
        self.__data = [None]*ListQueueDS.default_capacity
        self.__size = 0
        self.__front = 0
        self.__end = 0

    def __len_(self):
        return self.__size

    def is_empty(self):
        return self.__size == 0

    def first(self):
        if self.is_empty():
            print("The queue is empty.")
            return
        else:
            return self.__data[self.__front]

    def deque(self):
        if self.is_empty():
            print("The queue is empty.")
            return

        tmp = self.__data[self.__front]
        self.__data[self.__front] = None
        self.__front = (self.__front + 1) % ListQueueDS.default_capacity
        self.__size -= 1
        return tmp

    def enqueue(self, e):
        if self.__size == ListQueueDS.default_capacity:
            print("The queue is full!")
            return

        self.__data[self.__end] = e
        self.__end = (self.__end + 1) % ListQueueDS.default_capacity
        self.__size += 1

    def output(self):
        print(self.__data)

