document.addEventListener('DOMContentLoaded', function () {
	// user POST form (AJAX)
	const postForm = document.getElementById('postForm');
	if (postForm) {
		postForm.addEventListener('submit', async function (e) {
			e.preventDefault();
			const formData = new FormData(postForm);
			const params = new URLSearchParams();
			for (const [k, v] of formData.entries()) params.append(k, v);
			const res = await fetch('/user', {
				method: 'POST',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
				body: params.toString()
			});
			const text = await res.text();
			document.getElementById('postResult').textContent = text;
		});
	}

	// user GET form: prevent full navigation and fetch result instead
	const getForm = document.getElementById('getForm');
	if (getForm) {
		getForm.addEventListener('submit', async function (e) {
			e.preventDefault();
			const q = new URLSearchParams(new FormData(getForm)).toString();
			const res = await fetch('/user?' + q);
			document.getElementById('getResult').textContent = await res.text();
		});
	}

	// test GET (AJAX)
	const testForm = document.getElementById('testForm');
	if (testForm) {
		testForm.addEventListener('submit', async function (e) {
			e.preventDefault();
			const v = document.getElementById('valueInput').value || '';
			const res = await fetch('/test?value=' + encodeURIComponent(v));
			document.getElementById('testResult').textContent = await res.text();
		});
	}
});
