import { Seat } from 'src/app/model/seats';
export function SetSeatDependencyFloor(seat: any) {
  let seats: any;
  switch (seat.floor) {
    case 1:
      return seats;
    case 2:
      return Bus2Floor(seat);
    default:
      return seats;
  }
}

function Bus2Floor(seat: any) {
  let listSeatOfFloor1: any[] = [];
  let listSeatOfFloor2: any[] = [];

  let index = 0;
  let partSeats = seat.list_giuong.length/2;
  let seats:any = {seat:[]};

  seat.list_giuong = setSeatName(seat.list_giuong);
  console.log("aa");
  console.log(seat);

  for (let i = 0; i < seat.row; i++) {
    if(i<seat.row-2){
      seats = {
        seat: [
          seat.list_giuong[index],
          0,
          seat.list_giuong[index + 1],
          0,
          seat.list_giuong[index + 2],
        ],
      };
  
      listSeatOfFloor1.push(seats)
  
      seats = {
        seat: [
          seat.list_giuong[index + partSeats],
          0,
          seat.list_giuong[index + 1 + partSeats],
          0,
          seat.list_giuong[index + 2 + partSeats],
        ],
      };
  
      listSeatOfFloor2.push(seats)
    }else{
      if(i==seat.row-2){
        seats = {
          seat: [
            seat.list_giuong[index],
            0,
            0,            
            0,
            seat.list_giuong[index + 1],
          ],
        }
        listSeatOfFloor1.push(seats);
        seats = {
          seat: [
            seat.list_giuong[index + partSeats],
            0,
            0,            
            0,
            seat.list_giuong[index + 1 +partSeats],
          ],
        }
        listSeatOfFloor2.push(seats);
      }
      else{
        seats = {
          seat: [
            seat.list_giuong[index - 1],
            seat.list_giuong[index],
            seat.list_giuong[index + 1],            
            seat.list_giuong[index + 2],
            seat.list_giuong[index + 3],
          ],
        }
        listSeatOfFloor1.push(seats);
        seats = {
          seat: [
            seat.list_giuong[index + partSeats - 1],
            seat.list_giuong[index + partSeats],
            seat.list_giuong[index + 1 + partSeats],            
            seat.list_giuong[index + 2 + partSeats],
            seat.list_giuong[index + 3 +partSeats],
          ],
        }
        listSeatOfFloor2.push(seats);
      }
    }
    index += seat.srow;
  }
  let seatResult = [];
  seatResult.push(listSeatOfFloor1);
  seatResult.push(listSeatOfFloor2);
  return seatResult;
}


function setSeatName(seat:any){
  for(let i of seat){
    if(i.stt<10){
      i.name = ("A0"+i.stt);
    }
    else if(i.stt>=10&&i.stt<=22){
       i.name = ("A"+i.stt);
    }
    else if(i.stt-22<10&&i.stt-22>0){
       i.name = ("B0"+(i.stt-22));
    }
    else{
       i.name = ("B"+(i.stt-22));
    }
  }
  return seat;
}