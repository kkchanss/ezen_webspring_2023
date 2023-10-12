console.log('todo.js');

// 1. POST
function doPost(){

    let tcontent = document.querySelector(".tcontent");
    let dto = {tcontent : tcontent};
	$.ajax({
		url : "../",
		method : "post",
		data : {dto : JSON.stringify(dto)},
		dataType: 'json',
		ContentType : 'application/json; charset=utf-8',
		success : r => {

			if(r){ // 등록 성공
				alert('등록 성공');
				doGet();
			} else { // 등록 실패
				alert('등록 실패');
			}
		},
		error : e => {
			console.log(e);
		}
	})
}
// 2. GET
function doGet(){

	$.ajax({
		url : "../",
		method : "get",
		success : r => {
			console.log(r);
            let todo_bottom = document.querySelector(".todo_bottom");
            let html = "";
            for(let i = 0 ; i < r.length; i++) {

                if(r[i].tstate == true)
                html += `
                    <div class="todo">
                `;
                else{
                    html += `
                        <div class="todo successTodo">
                    `;
                }
                html += `
                    <div class="tcontent"> r[i].tcontent </div>
                    <div class="etcbtns">
                        <button onclick="doPut(${r[i].tno}, ${r[i].tstate})" type="button">상태변경</button>
                        <button onclick="doDelete(${r[i].tno})" type="button">제거하기</button>
                    </div>
                </div>
                `;
            }
            todo_bottom.innerHTML = html;
		},
		error : e => {
			console.log(e);
		}
	})
}
// 3. PUT
function doPut(tno, tstate){

	$.ajax({
		url : "../",
		method : "put",
		data : {tstate : tstate, tno : tno},
		success : r => {

			if(r){ // 등록 성공
				alert('상태 변경 성공');
				doGet();
			} else { // 등록 실패
				alert('상태 변경 실패');
			}
		},
		error : e => {
			console.log(e);
		}
	})
}
// 4. DELETE
function doDelete(tno){

	$.ajax({
		url : "../",
		method : "delete",
		data : {tno : tno},
		success : r => {

			if(r){
				alert('삭제 성공');
				doGet();
			} else {
				alert('삭제 실패');
			}
		},
		error : e => {
			console.log(e);
		}
	})
}