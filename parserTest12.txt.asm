# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	varignore: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: x
	#number
	li $v0, 1
	la $t0 varx
	sw $v0 ($t0)
	#assignment: ignore
	#procedure call: printSquare
	subu $sp $sp 4
	sw $ra ($sp)
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	jal procprintSquare
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	li $v0, 10
	syscall
	
	#procedure declaration: printSquare
procprintSquare:
	subu $sp $sp 4
	sw $zero ($sp)
	#writeln
	#binary operand
	#variable: n
	lw $v0 4($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: n
	lw $v0 8($sp)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
