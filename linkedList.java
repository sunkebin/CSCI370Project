
public class linkedList {
	patient head;
	patient tail;
	int length;
	
	linkedList(){
		length=0;
		head=new patient();//dummy node
		tail= head;
	}
	
	void add(patient a) {
		length++;
		tail.next =a;
		tail= tail.next;
		
	}
	
	void removehead() {
		length--;
		if(head.next!=tail) {
			patient temp=head.next;
			head.next=head.next.next;
			temp.next=null;
		}else {
			patient temp=head.next;
			head.next=head.next.next;
			temp.next=null;
			tail=head;
		}
	}
}