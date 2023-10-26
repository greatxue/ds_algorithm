from BST_DS import BST_DS


class testBST:
    def test_insert(self):
        BST = BST_DS()
        BST.insert(3)
        assert BST.to_list() == [3]


