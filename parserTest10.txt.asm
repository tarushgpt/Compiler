# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varf: .word 0
	varignore: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: f
	#number
	li $v0, 2
	la $t0 varf
	sw $v0 ($t0)
	#assignment: ignore
	#procedure call: foo
	subu $sp $sp 4
	sw $ra ($sp)
	#number
	li $v0, 3
	subu $sp $sp 4
	sw $v0 ($sp)
	jal procfoo
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	#writeln
	#variable: f
	la $t0 varf
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
	#procedure declaration: foo
procfoo:
	subu $sp $sp 4
	sw $zero ($sp)
	#assignment: ignore
	#procedure call: bar
	subu $sp $sp 4
	sw $ra ($sp)
	#binary operand
	#variable: d
	lw $v0 8($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: f
	la $t0 varf
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	jal procbar
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
	#procedure declaration: bar
procbar:
	subu $sp $sp 4
	sw $zero ($sp)
	#writeln
	#variable: f
	lw $v0 4($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
