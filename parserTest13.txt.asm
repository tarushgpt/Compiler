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
	#assignment: x
	#procedure call: countUp
	subu $sp $sp 4
	sw $ra ($sp)
	#number
	li $v0, 2
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 4
	subu $sp $sp 4
	sw $v0 ($sp)
	jal proccountUp
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varx
	sw $v0 ($t0)
	li $v0, 10
	syscall
	
	#procedure declaration: countUp
proccountUp:
	subu $sp $sp 4
	sw $zero ($sp)
	#if
	#condition
	#variable: count
	lw $v0 8($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: max
	lw $v0 8($sp)
	lw $t0 ($sp)
	addu $sp $sp 4
	bgt $t0 $v0 endif1
	#block
	#writeln
	#variable: count
	lw $v0 8($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: ignore
	#procedure call: countUp
	subu $sp $sp 4
	sw $ra ($sp)
	#binary operand
	#variable: count
	lw $v0 12($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: max
	lw $v0 12($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	jal proccountUp
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
endif1:
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
