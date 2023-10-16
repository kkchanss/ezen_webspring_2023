console.log('phone.js');

doGet();
// 1. POST
function doPost(){

    let pname = document.querySelector(".input_pname");
    let pphone = document.querySelector(".input_pphone");

    let dto = { pno : 1, pname : pname.value, pphone : pphone.value };
	$.ajax({
		url : "/phone",
		method : "post",
		contentType : 'application/json',
		data : JSON.stringify(dto),

		success : r => {

			if(r){ // 등록 성공
				alert('등록 성공');
				pname.value = "";
				pphone.value = "";
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
		url : "/phone",
		method : "get",
		success : r => {
            let phone_bottom = document.querySelector(".phone_bottom");
            let html = "";
            for(let i = 0 ; i < r.length; i++) {

                html += `
                    <div class="phone">
                        <div class="pname"> ${r[i].pname} </div>
                        <div class="pphone"> ${r[i].pphone} </div>
                        <div class="btns">
                            <button onclick="doPut(${r[i].pno})" type="button">수정하기</button>
                            <button onclick="doDelete(${r[i].pno})" type="button">제거하기</button>
                        </div>
                    </div>
                `;
            }
            phone_bottom.innerHTML = html;
		},
		error : e => {
			console.log(e);
		}
	})
}
// 3. PUT
function doPut(pno){

    let pname = prompt("이름을 입력해주세요");
    let pphone = prompt("전화번호를 입력해주세요");

    let dto = { pno : pno, pname : pname, pphone : pphone}

	$.ajax({
		url : "/phone",
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
function doDelete(pno){

	$.ajax({
		url : "/phone",
		method : "delete",
		data : {pno : pno},
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