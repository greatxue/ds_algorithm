import abc


class StackDS(metaclass=abc.ABCMeta):
    """ This is an interface of the stack.
     A stack is a collection of objects that are inserted and removed according to the "LIFO" principle.
     A user may insert objects into a stack at any time, but may only access or remove the "top" of the stack."""

    @abc.abstractmethod
    def __init__(self):
        """ Initialize an empty stack."""
        pass

    @abc.abstractmethod
    def __len__(self):
        """ Return the number of elements in the stack."""
        pass

    @abc.abstractmethod
    def is_empty(self):
        """ Check if the stack is empty."""
        pass

    @abc.abstractmethod
    def push(self, e):
        """ Push an element onto the top of the stack. """
        pass

    @abc.abstractmethod
    def top(self):
        """ Return the element at the top of the stack."""
        pass

    @abc.abstractmethod
    def pop(self):
        """ Remove and return the element at the top of the stack."""
        pass
