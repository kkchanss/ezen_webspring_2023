console.log('todo.js');

doGet();
// 1. POST
function doPost(){

    let tcontent = document.querySelector(".input_tcontent");
    console.log(tcontent.value)
    let dto = { tno : 1, tcontent : tcontent.value, tstate : true };
	$.ajax({
		url : "/todo",
		method : "post",
		contentType : 'application/json',
		data : JSON.stringify(dto),

		success : r => {

			if(r){ // 등록 성공
				alert('등록 성공');
				tcontent.value = "";
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
		url : "/todo",
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
                    <div class="tcontent"> ${r[i].tcontent} </div>
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

    tstate = !tstate

    let dto = { tno : tno, tstate : tstate, tcontent : "test"}

	$.ajax({
		url : "/todo",
		method : "put",
		contentType : 'application/json',
        data : JSON.stringify(dto),
		success : r => {

			if(r){
				console.log('상태 변경 성공');

				doGet();
			} else {
				console.log('상태 변경 실패');
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
		url : "/todo",
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