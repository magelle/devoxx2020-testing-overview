/// <reference types="cypress" />

context('Basket', () => {

    it('A Pommes, A Bananes and a Cerises should be 325€', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Bananes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Cerises', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 325 €');
    });

    it('2 Bananes should be 150 €', () => {
        cy.visit('http://localhost:4200')
        
        cy.contains('Bananes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Bananes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        
        cy.contains('Total 150 €');
    });

    it('2 Cerises should be 130 €', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Cerises', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Cerises', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 130 €');
    });

    it('3 Apples should be 200 €', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Apples', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Apples', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Apples', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 200 €');
    });

    it('2 Mele should be 200 €', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Mele', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Mele', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 100 €');
    });
    
    it('4 Pommes should be 400 €', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 300 €');
    });

 
    it('4 Pommes and a Bananes should be 400 €', () => {
        cy.visit('http://localhost:4200')

        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Bananes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();
        cy.contains('Pommes', {timeout: 1500}).parent().contains('+', {timeout: 1500}).click();

        cy.contains('Total 250 €');
    });

})