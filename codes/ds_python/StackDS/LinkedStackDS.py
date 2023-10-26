from StackDS import StackDS


class Node:
    def __init__(self, element, pointer):
        self.element = element
        self.pointer = pointer


class LinkedStack:
    def __init__(self):
        self.head = None
        self.size = 0

    def len(self):
        return self.size


class LinkedStackDS(StackDS):
    def __init__(self):
        pass

    def __len__(self):
        pass

    def is_empty(self):
        pass

    def push(self, e):
        pass

    def top(self):
        pass

    def pop(self):
        pass
