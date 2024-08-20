document.addEventListener('DOMContentLoaded', function() {
    const showBooksBtn = document.getElementById('show-books');
    showBooksBtn.addEventListener('click', loadBooks);
});

function loadBooks() {
    fetch('/libralink-book/list')
        .then(response => response.json())
        .then(books => {
            const booksList = document.getElementById('books-list');
            booksList.innerHTML = ''; // Clear the list before adding new items
            books.forEach(book => {
                const bookItem = document.createElement('div');
                bookItem.className = 'book-item';
                bookItem.innerHTML = `
                    <p>Title: ${book.title}</p>
                    <p>Author: ${book.author}</p>
                    <p>Author: ${book.isbn}</p>
                    <p>Author: ${book.genre}</p>
                    <p>Author: ${book.publisher}</p>
                    <p>Author: ${book.publicationDate}</p>
                    
                    <button onclick="updateBook(${book.id})">Update</button>
                    <br>
                    <button onclick="deleteBook(${book.id})">Delete</button>
                `;
                booksList.appendChild(bookItem);
            });
        })
        .catch(error => console.error('Error loading books:', error));
}
