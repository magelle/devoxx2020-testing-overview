# Tour d'horizon des tests dans nos applications

TDD? BDD? E2E? Quoi tester ? comment ? Quelle stratégie de tests pour quelles parties du code ? Beaucoup de questions se posent autour des tests. On en parle beaucoup mais comment fait-on dans la vraie vie ?

Mais nous ne nous arrêterons pas là. Nous vérifierons la qualité de nos tests avec le mutation testing et des interactions avec le contract testing. Nous allons également générer des tests à travers le property based testing.

Alternant théorie et live coding, nous ferons le tour de ces sujets et des outils les plus adaptés.
Venez découvrir l’état de l’art du test pour créer des applications de haute qualité !

## Todo

- Sujet du live coding
- Déroulé
- Outils à utiliser ?
- Ordre de présentation des teypes de tests

## 
- Tests manuels
    - avantages / limites
- Non reg
    - avantages / limites
- E2E
    - avantages / limitations
- Couverture -> mutation testing
- Mutation testing : effet waouh ?
  - Fruit shop kata
  - prix negatif ?
- Property base testing
- Contract testing

Ouverture
- Simulation testing
- Chaos testing
- 

### Déroulé

- j'ai déjà un projet, pas beaucoup de tests
- je vais chercher en base uniquement les réductions des objets en panier
  - une partie du code métier est dans la requête SQL
  - j'ai un base pour tester
  - Introduction tests FIRST
  - introduction archi hexagonale
- Les réductions doivent être hitorisées, (on doit connaitre qu'elle réduc a été appliquée pour la facturation)
  - bounded context

- La création de réduction et le calcul du prix du panier sont au même endroit
  - introduction bounded context aka plusieurs hexagones
  - séparation des deux context et communication par message ?

- les tester les messages : contract testing

/!\ Bug on a des panier avec des prix négatif !!!!!
- comment voir d'où ça vient
- comment s'assurer de plus jamais avoir ça
- Introduction rpoperty base testing
- mise en place

