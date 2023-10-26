import abc


class QueueDS(metaclass=abc.ABCMeta):
    """ This is an interface of the queue.
        A queue is a collection of objects that are inserted and removed according to the "FIFO" principle.
        Elements can be inserted at any time, but only the element in the queue the longest can be next removed."""

    @abc.abstractmethod
    def __init__(self):
        """ Initialize an empty stack."""
        pass

    @abc.abstractmethod
    def __len_(self):
        """Return the length of the queue."""
        pass

    @abc.abstractmethod
    def is_empty(self):
        """Return whether the queue is empty."""
        pass

    @abc.abstractmethod
    def first(self):
        """Return the front element of the queue."""
        pass

    @abc.abstractmethod
    def deque(self):
        """Return and remove the front element."""
        pass

    @abc.abstractmethod
    def enqueue(self, e):
        """Add an element to the front of the queue."""
        pass



