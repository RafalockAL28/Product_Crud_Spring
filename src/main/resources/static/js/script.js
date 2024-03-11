// Função para manipular a edição do produto
function editProduct(id) {
    const row = document.querySelector(`#productList tr[data-id="${id}"]`);
    const name = row.children[1].textContent;
    const type = row.children[2].textContent;
    const price = row.children[3].textContent;
    const qtde = row.children[4].textContent;

    document.getElementById('name').value = name;
    document.getElementById('type').value = type;
    document.getElementById('price').value = price;
    document.getElementById('qtde').value = qtde;

    // Altera o texto e o evento do botão para 'Confirmar'
    document.getElementById('submitBtn').innerText = 'Confirm';
    document.getElementById('submitBtn').removeEventListener('click', addProduct); // Remove o evento de adicionar produto
    document.getElementById('submitBtn').addEventListener('click', () => updateProduct(id)); // Adiciona o evento de atualizar produto
}

// Função para adicionar um novo produto
async function addProduct() {
    console.log('addProduct function called');
    event.preventDefault(); // Impede o envio do formulário
    const formData = new FormData(document.getElementById('productForm'));
    const productData = {
        name: formData.get('name'),
        type: formData.get('type'),
        price: parseFloat(formData.get('price')), // Converter para número
        qtde: parseInt(formData.get('qtde')) // Converter para número inteiro
    };
    console.log('Product data:', productData); // Log dos dados do produto
    const response = await fetch('/product', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    });
    if (response.ok) {
        await fetchProducts();
        document.getElementById('productForm').reset();
        console.log('Product added successfully'); // Log de sucesso ao adicionar o produto
    } else {
        alert('Failed to add product');
        console.error('Failed to add product:', response.status); // Log de erro ao adicionar o produto
    }
}

// Função para atualizar o produto
async function updateProduct(id) {
    event.preventDefault(); // Impede o envio do formulário
    const formData = new FormData(document.getElementById('productForm'));
    const response = await fetch(`/product/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Object.fromEntries(formData))
    });
    if (response.ok) {
        await fetchProducts(); // Atualiza a tabela de produtos
        document.getElementById('productForm').reset(); // Limpa o formulário
        document.getElementById('submitBtn').innerText = 'Add Product'; // Restaura o texto do botão
        document.getElementById('submitBtn').removeEventListener('click', updateProduct); // Remove o evento de atualizar produto
        document.getElementById('submitBtn').addEventListener('click', addProduct); // Adiciona o evento de adicionar produto
    } else {
        alert('Failed to update product');
    }
}


// Função para deletar um produto
async function deleteProduct(id) {
    const response = await fetch(`/product/${id}`, {
        method: 'DELETE'
    });
    if (response.ok) {
        await fetchProducts(); // Atualiza a tabela de produtos
    } else {
        alert('Failed to delete product');
    }
}
document.getElementById('submitBtn').addEventListener('click', addProduct); // Adiciona o evento de adicionar produto

// Função para buscar os produtos
async function fetchProducts() {
    const response = await fetch('/product');
    const data = await response.json();
    const productList = document.getElementById('productList');
    productList.innerHTML = ''; // Limpa a lista de produtos antes de adicionar os novos

    data.forEach(product => {
        const row = document.createElement('tr');
        row.setAttribute('data-id', product.id);
        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.type}</td>
            <td>${product.price}</td>
            <td>${product.qtde}</td>
            <td>
                <button class="btn btn-primary btn-sm" onclick="editProduct(${product.id})">Edit</button>
                <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.id})">Delete</button>
            </td>
        `;
        productList.appendChild(row);
            document.getElementById('submitBtn').addEventListener('click', addProduct); // Adiciona o evento de adicionar produto

    });
}

// Chama a função fetchProducts quando a página é carregada
window.onload = fetchProducts;