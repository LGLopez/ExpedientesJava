package expedientes;

import java.util.Objects;

public class BinaryTree {

	Node root;

	public void addNode(Long key, String name, String resto) {
                Node newNode = new Node(key, name, resto);

		if (root == null) {

			root = newNode;

		} else {
			Node focusNode = root;

			Node parent;

			while (true) {
                            	parent = focusNode;

			
				if (key < focusNode.key) {

					focusNode = focusNode.leftChild;

					if (focusNode == null) {


						parent.leftChild = newNode;
						return; 

					}

				} else { 

					focusNode = focusNode.rightChild;

					if (focusNode == null) {

						parent.rightChild = newNode;
						return; 

					}

				}

			}
		}

	}

	public Node findNode(Long key) {
		Node focusNode = root;

		while (!Objects.equals(focusNode.key, key)) {

			if (key < focusNode.key) {

				focusNode = focusNode.leftChild;

			} else {

				focusNode = focusNode.rightChild;

			}

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}
}

class Node {

	Long key;
	String name;
        String restoExpediente;

	Node leftChild;
	Node rightChild;

	Node(Long key, String name, String restoExpediente) {

		this.key = key;
		this.name = name;
                this.restoExpediente = restoExpediente;
                
	}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestoExpediente() {
        return restoExpediente;
    }

    public void setRestoExpediente(String restoExpediente) {
        this.restoExpediente = restoExpediente;
    }
        
        
        
	public String toString() {

		return name + "\r\n" + restoExpediente;

	}

}