package gameClasses;

public class Snapshot{
	private Field[][] field;
	char color;
	char next;
	int fieldSize;
	public Snapshot(Field[][] data,char col, int fieldSize,char next){
		int count1,count2;
		this.next=next;
		this.fieldSize=fieldSize;
		field  = new Field[fieldSize][fieldSize];
		color = col;
		for(count1=0;count1<fieldSize;count1++)
			for(count2=0;count2<fieldSize;count2++)
				field[count1][count2]=data[count1][count2];
	}

	public String toStringTest(){
		StringBuilder response=new StringBuilder();
		response.append("{'status':'snapshot',");
		response.append("'next':'").append(next).append("',");
		if(color=='w')
			response.append("'color':'w',");
		else
			response.append("'color':'b',");
		response.append("'field':");
		int fieldSizeCount;
        int innerCount;
		response.append("[");
		for(fieldSizeCount=0;fieldSizeCount<fieldSize;fieldSizeCount++){
			if(fieldSizeCount!=0)
				response.append(", ");
			response.append("[");
			for(innerCount=0;innerCount<fieldSize;innerCount++){
				if(innerCount!=0)
					response.append(", ");
				response.append("'").append(field[fieldSizeCount][innerCount].getType()).append("'");
			}
			response.append("]");
		}
		response.append("]");
		response.append(",'king':");
		response.append("[");
		for(fieldSizeCount=0;fieldSizeCount<fieldSize;fieldSizeCount++){
			if(fieldSizeCount!=0)
				response.append(", ");
			response.append("[");
			for(innerCount=0;innerCount<fieldSize;innerCount++){
				if(innerCount!=0)
					response.append(", ");
				response.append("'").append(field[fieldSizeCount][innerCount].isKing()).append("'");
			}
			response.append("]");
		}
		response.append("]");
		response.append("}");
		return response.toString();
	}

    @Override
    public String toString(){
        StringBuilder response=new StringBuilder();
        response.append("{\"status\":\"snapshot\",");
        response.append("\"next\":\"").append(next).append("\",");
        if(color=='w')
            response.append("\"color\":\"w\",");
        else
            response.append("\"color\":\"b\",");
        response.append("\"field\":");
        int fieldSizeCount;
        int innerCount;
        response.append("[");
        for(fieldSizeCount=0;fieldSizeCount<fieldSize;fieldSizeCount++){
            if(fieldSizeCount!=0)
                response.append(", ");
            response.append("[");
            for(innerCount=0;innerCount<fieldSize;innerCount++){
                if(innerCount!=0)
                    response.append(", ");
                response.append("\"").append(field[fieldSizeCount][innerCount].getType()).append("\"");
            }
            response.append("]");
        }
        response.append("]");
        response.append(",\"king\":");
        response.append("[");
        for(fieldSizeCount=0;fieldSizeCount<fieldSize;fieldSizeCount++){
            if(fieldSizeCount!=0)
                response.append(", ");
            response.append("[");
            for(innerCount=0;innerCount<fieldSize;innerCount++){
                if(innerCount!=0)
                    response.append(", ");
                response.append("\"").append(field[fieldSizeCount][innerCount].isKing()).append("\"");
            }
            response.append("]");
        }
        response.append("]");
        response.append("}");
        return response.toString();
    }
}