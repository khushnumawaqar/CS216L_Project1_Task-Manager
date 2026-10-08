## Project Title
Task Manager

##Group Name
CS216L_Project1_Task Manager

##Group Members:
1)Khushnuma Waqar(3)
2)Malaika Shoaib(48)
3)Maryam Barkat(28)

## Description
A menu-driven Java console application for managing tasks. Tasks are stored in a
linked list, and every add, delete and complete action is pushed onto a stack so
the last action can be undone. Tasks can also be searched and sorted by priority.

## Data Structures Used
- Singly linked list – stores the tasks
- Stack – stores actions for undo (push, pop, peek, isEmpty, size)
- Linear search – finds tasks by ID or title
- Bubble sort – sorts tasks by priority

## Features
1. Add task
2. Delete task
3. Search task (linear search)
4. Sort tasks by priority (bubble sort)
5. View all tasks
6. Undo last action (stack)
7. Mark task complete
8. Peek last action

## Time Complexity (n = number of tasks)
| Operation | Time Complexity |
|-----------|-----------------|
| Insert | O(1) (add at end using tail pointer) |
| Delete | O(n) |
| Search (linear) | O(n) |
| Sort (bubble) | O(n²) |
| Push | O(1) |
| Pop | O(1) |


