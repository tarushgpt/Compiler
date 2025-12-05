# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	vary: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: x
	#number
	li $v0, 2
	la $t0 varx
	sw $v0 ($t0)
	#assignment: y
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 vary
	sw $v0 ($t0)
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	#writeln
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
